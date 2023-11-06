package services;

import com.clothingstore.bus.CustomerBUS;
import com.clothingstore.bus.ImportBUS;
import com.clothingstore.bus.ImportItemsBUS;
import com.clothingstore.bus.OrderItemBUS;
import com.clothingstore.bus.PaymentBUS;
import com.clothingstore.bus.PaymentMethodBUS;
import com.clothingstore.bus.ProductBUS;
import com.clothingstore.bus.UserBUS;
import com.clothingstore.gui.utilities.ImageUtil;
import com.clothingstore.models.CustomerModel;
import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.ImportModel;
import com.clothingstore.models.OrderItemModel;
import com.clothingstore.models.OrderModel;
import com.clothingstore.models.PaymentMethodModel;
import com.clothingstore.models.PaymentModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.UserModel;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.image.BufferedImage;
import javax.swing.JTable;

public class PDFWriter {

  private static final String FONT_FILE_PATH = "./src/main/java/resources/fonts/Arial.ttf";
  private static PDFWriter instance;

  public Document document;
  private Font fontData;
  private Font fontTitle;
  private Font fontHeader;

  private PDFWriter() {
    try {
      initializeFonts();
    } catch (IOException | DocumentException e) {
      throw new RuntimeException("Failed to initialize fonts", e);
    }
  }

  public static synchronized PDFWriter getInstance() {
    if (instance == null) {
      instance = new PDFWriter();
    }
    return instance;
  }

  private void initializeFonts() throws IOException, DocumentException {
    BaseFont baseFont = BaseFont.createFont(
        FONT_FILE_PATH,
        BaseFont.IDENTITY_H,
        BaseFont.EMBEDDED);
    fontData = new Font(baseFont, 11, Font.NORMAL);
    fontTitle = new Font(baseFont, 25, Font.NORMAL);
    fontHeader = new Font(baseFont, 13, Font.NORMAL);
  }

  public void chooseURL(String filePath) {
    try {
      document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(filePath + ".pdf"));
      document.open();
    } catch (IOException | DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to open PDF file", ex);
    }
  }

  public void setTitle(String title) {
    Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
    pdfTitle.setAlignment(Element.ALIGN_CENTER);
    try {
      document.add(pdfTitle);
      document.add(new Paragraph(Chunk.NEWLINE));
    } catch (DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to write title to PDF", ex);
    }
  }

  public void writeObject(String[] data) {
    for (String datum : data) {
      Paragraph pdfData = new Paragraph(datum, fontData);
      pdfData.setSpacingAfter(10f); // Add some spacing after each line
      try {
        document.add(pdfData);
      } catch (DocumentException ex) {
        Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
        throw new RuntimeException("Failed to write object to PDF", ex);
      }
    }
  }

  public void writeTable(JTable table) {
    PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
    pdfTable.setHeaderRows(1);
    for (int i = 0; i < table.getColumnCount(); i++) {
      String header = table.getColumnName(i);
      PdfPCell cell = new PdfPCell(new Phrase(header, fontHeader));
      pdfTable.addCell(cell);
    }
    for (int i = 0; i < table.getRowCount(); i++) {
      for (int j = 0; j < table.getColumnCount(); j++) {
        String data = String.valueOf(table.getValueAt(i, j));
        PdfPCell cell = new PdfPCell(new Phrase(data, fontData));
        pdfTable.addCell(cell);
      }
    }
    try {
      document.add(pdfTable);
    } catch (DocumentException ex) {
      Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Failed to write table to PDF", ex);
    }
  }

  public void close() {
    if (document != null) {
      document.close();
    }
  }

  public void exportImportsToPDF(int importId, String filepath) {
    ImportModel importData = ImportBUS.getInstance().getModelById(importId);
    List<ImportItemsModel> importItemsList = ImportItemsBUS
        .getInstance()
        .getAllModels();
    List<ImportItemsModel> modifiableItemsList = new ArrayList<>(
        importItemsList);
    modifiableItemsList.removeIf(items -> items.getImport_id() != importData.getId());

    List<ProductModel> productsList = ProductBUS.getInstance().getAllModels();
    List<ProductModel> modifiableProductsList = new ArrayList<>(productsList);
    for (int i = modifiableItemsList.size() - 1; i >= 0; i--) {
      boolean found = false;
      for (ProductModel productModel : modifiableProductsList) {
        if (modifiableItemsList.get(i).getId() == productModel.getId()) {
          found = true;
          break;
        }
      }
      if (!found) {
        modifiableItemsList.remove(i);
      }
    }

    UserModel employee = UserBUS
        .getInstance()
        .getModelById(importData.getUserId());

    // Calculate Total Price
    double totalPrice = 0;
    for (ImportItemsModel items : modifiableItemsList) {
      ProductModel productModel = ProductBUS.getInstance().getModelById(items.getId());
      double itemTotalPrice = (items.getQuantity() * productModel.getPrice());
      totalPrice += itemTotalPrice;
    }

    // Format Total Price as Currency
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    String formattedTotalPrice = currencyFormatter.format(totalPrice);

    chooseURL(filepath);
    try {
      setTitle("Import Receipt");

      // Add header information
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String headerInfoString = "ID: " +
          importData.getId() +
          "\n" +
          "Employee: " +
          employee.getName() +
          "\n" +
          "Total Price: " +
          /* importData.getTotalPrice() */formattedTotalPrice +
          "\n" +
          "Created At: " +
          dateFormat.format(importData.getImportDate());
      writeObject(headerInfoString.split("\n"));

      // Add product Information
      String[] columnNames = {
          "ID",
          "Image",
          "Name",
          "Price",
          "Quantity",
          "Total Price",
      };
      Object[][] data = new Object[modifiableItemsList.size()][5];
      for (int i = 0; i < modifiableItemsList.size(); i++) {
        ImportItemsModel item = modifiableItemsList.get(i);
        ProductModel product = ProductBUS
            .getInstance()
            .getModelById(item.getId());
        double itemTotalPrice = item.getQuantity() * product.getPrice();
        data[i][0] = product.getId();
        data[i][1] = product.getName();

        try {
          BufferedImage originalImage = ImageUtil.fromBase64(product.getImage());
          data[i][2] = originalImage;
        } catch (IOException e) {
          e.printStackTrace();
        }
        data[i][3] = "$" + product.getPrice();
        data[i][4] = item.getQuantity();
        data[i][5] = currencyFormatter.format(itemTotalPrice);
      }
      JTable table = new JTable(data, columnNames);
      writeTable(table);

      // Close the document
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void exportReceiptToPDF(OrderModel orderModel, String filepath) {
    // Get List products in cart items:
    List<OrderItemModel> orderItemsList = OrderItemBUS
        .getInstance()
        .getAllModels();
    List<OrderItemModel> modifiableOrderItemsList = new ArrayList<>(
        orderItemsList);
    modifiableOrderItemsList.removeIf(cartItem -> cartItem.getOrderId() != orderModel.getId());

    // Get product information:
    List<ProductModel> products = ProductBUS.getInstance().getAllModels();
    List<ProductModel> modifiableProductList = new ArrayList<>(products);
    for (int i = modifiableOrderItemsList.size() - 1; i >= 0; i--) {
      boolean found = false;
      for (ProductModel product : modifiableProductList) {
        if (modifiableOrderItemsList.get(i).getProductId() == product.getId()) {
          found = true;
          break;
        }
      }
      if (!found) {
        modifiableOrderItemsList.remove(i);
      }
    }

    // Get Customer Data
    CustomerModel customer = CustomerBUS
        .getInstance()
        .getModelById(orderModel.getCustomerId());
    if (customer == null) {
      customer = new CustomerModel();
      customer.setCustomerName("Guest");
    }

    // Get Employee Information
    UserModel employee = UserBUS
        .getInstance()
        .getModelById(orderModel.getUserId());

    // Get Payment & payment method
    List<PaymentModel> paymentData = PaymentBUS
        .getInstance()
        .searchModel(
            String.valueOf(orderModel.getId()),
            new String[] { "order_id" });
    PaymentMethodModel paymentMethod = null;
    PaymentModel payment = null;
    if (paymentData.size() == 1) {
      payment = paymentData.get(0);
      paymentMethod = PaymentMethodBUS.getInstance().getModelById(payment.getId());
    }

    // Calculate Total Price
    double totalPrice = 0;
    for (OrderItemModel cartItem : modifiableOrderItemsList) {
      ProductModel product = ProductBUS
          .getInstance()
          .getModelById(cartItem.getProductId());
      double itemTotalPrice = (cartItem.getQuantity() * product.getPrice());
      totalPrice += itemTotalPrice;
    }

    // Format Total Price as Currency
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    String formattedTotalPrice = currencyFormatter.format(totalPrice);

    // Add receipt information into PDF File.
    chooseURL(filepath);
    try {
      setTitle("Purchase Receipt");

      // Add Order Information
      Timestamp orderDateTimestamp = orderModel.getOrderDate(); // Assuming orderDate is a Timestamp
      LocalDate orderDate = orderDateTimestamp.toLocalDateTime().toLocalDate();
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String orderInfoString = "Order Date: " +
          dateFormat.format(orderDate) +
          "\n" +
          "Order ID: " +
          orderModel.getId() +
          "\n" +
          "Customer Name: " +
          customer.getCustomerName() +
          "\n" +
          "Employee Name: " +
          employee.getName() +
          "\n" +
          "Payment Method: " +
          (paymentMethod != null ? payment.getPaymentMethodId() : "") +
          "\n" +
          "Total Amount: " +
          formattedTotalPrice;
      writeObject(orderInfoString.split("\n"));

      // Add product Information
      String[] columnNames = {
          "ID",
          "Image",
          "Name",
          "Price",
          "Quantity",
          "Total Price",
      };
      Object[][] data = new Object[modifiableOrderItemsList.size()][6];
      for (int i = 0; i < modifiableOrderItemsList.size(); i++) {
        OrderItemModel orderItemModel = modifiableOrderItemsList.get(i);
        ProductModel product = ProductBUS
            .getInstance()
            .getModelById(orderItemModel.getProductId());
        double itemTotalPrice = orderItemModel.getQuantity() * product.getPrice();
        data[i][0] = product.getId();
        try {
          BufferedImage originalImage = ImageUtil.fromBase64(product.getImage());
          data[i][1] = originalImage;
        } catch (IOException e) {
          e.printStackTrace();
        }
        data[i][2] = product.getName();
        data[i][3] = product.getPrice();
        data[i][4] = orderItemModel.getQuantity();
        data[i][5] = currencyFormatter.format(itemTotalPrice);
      }
      JTable table = new JTable(data, columnNames);
      writeTable(table);

      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}