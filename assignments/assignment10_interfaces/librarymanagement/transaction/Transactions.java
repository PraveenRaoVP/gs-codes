package assignments.assignment10_interfaces.librarymanagement.transaction;

public class Transactions {
    private int transactionId;
    private int bookId;
    private int memberId;
    private String transactionType;
    private String transactionDate;

    public Transactions(int transactionId, int bookId, int memberId, String transactionType, String transactionDate) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "Transactions [bookId=" + bookId + ", memberId=" + memberId + ", transactionDate=" + transactionDate
                + ", transactionId=" + transactionId + ", transactionType=" + transactionType + "]";
    }
}
