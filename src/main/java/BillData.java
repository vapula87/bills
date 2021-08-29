import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Date;
import java.util.ArrayList;

public class BillData {
    private ObservableList<Entry> entries;
    private ArrayList<Bill> bills;
    public BillData() {
        initBills();
        initEntries();
    }
    public ArrayList<Bill> getBills() { return bills; }
    public ObservableList<Entry> getEntries() { return entries; }
    public void initBills() { bills = new ArrayList<>(); }
    public void initEntries() { entries = FXCollections.observableArrayList(); }
    public void addBill(String name, boolean status) {
        bills.add(new Bill(name, status));
    }
    public void addEntry(int id, String name, Date date, float amount, int status, String notes) {
        entries.add(new Entry(id, name, date, amount, status, notes));
    }
    public class Entry {
        ArrayList<Payment> payments;
        int id;
        String name;
        Date date;
        float amount;
        int status;
        String notes;
        private ImageView image;


        Entry(int id, String name, Date date, float amount,
              int status, String n) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.amount = amount;
            this.status = status;
            notes = n;
            setImage(status);
        }
        public void addPayment(Payment payment) {
            //Check for duplicate?
            payments.add(payment);
        }
        public int getId() { return id; }
        public String getName() { return name; }
        public Date getDate() { return date; }
        public float getAmount() { return amount; }
        public int getStatus() { return status; }
        public String getNotes() { return notes; }
        public ImageView getImage() { return image; }
        public void setImage(int status) {
            Image img;
            switch (status) {
                case 0:
                    img = new Image("x-mark-16.jpg");
                    break;
                case 1:
                    img = new Image("checkmark-16.jpg");
                    break;
                default:
                    img = new Image("warning-16.jpg");
            }
            image = new ImageView(img);
        }

        private class Payment {
            int id;
            int entryID;
            Date date;
            float amount;
            String type;
            String notes;
            Payment(int id, int eID, Date date, float a, String t, String n) {
                this.id = id;
                entryID = eID;
                this.date = date;
                amount = a;
                type = t;
                notes = n;
            }
        }
    }
    public class Bill {
        private String name;
        private boolean isActive;
        public Bill(String name, boolean isActive) {
            this.name = name;
            this.isActive = isActive;
        }
        public String getName() { return name; }
        public boolean isActive() { return isActive; }
    }
}