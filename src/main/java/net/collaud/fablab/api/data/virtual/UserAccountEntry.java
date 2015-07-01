package net.collaud.fablab.api.data.virtual;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.api.data.MotionStockEO;
import net.collaud.fablab.api.data.PurchaseEO;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.UserPaymentEO;

import net.collaud.fablab.api.data.type.HistoryEntryType;
import static net.collaud.fablab.api.data.type.HistoryEntryType.PAYMENT;
import static net.collaud.fablab.api.data.type.HistoryEntryType.PURCHASE;
import static net.collaud.fablab.api.data.type.HistoryEntryType.SUBSCRIPTION;
import static net.collaud.fablab.api.data.type.HistoryEntryType.USAGE;
import static net.collaud.fablab.api.data.type.RefundAction.CREDIT;
import static net.collaud.fablab.api.data.type.RefundAction.REFUND;

/**
 *
 * @author Fabien Vuilleumier
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"ID", "TYPE"})
public class UserAccountEntry implements Comparable<UserAccountEntry> {

    private final int ID;
    private final HistoryEntryType TYPE;
    private final String COMMENT;
    private final Date DATE;
    private final Double AMOUNT;
    private final String DETAIL;
    private final HistoryEntryUser USER;

    public UserAccountEntry(UserPaymentEO payment, UserEO user) {
        boolean cancel = !payment.isActive();
        ID = payment.getId();
        USER = new HistoryEntryUser(user);
        TYPE = PAYMENT;
        DATE = payment.getDatePayment();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(payment.isActive() ? "" : "Canceled");
        commentSb.append(payment.isActive() ? "" : " | ");
        commentSb.append(payment.getLabel());
        COMMENT = commentSb.toString();
        DETAIL = payment.getNote() == null ? "" : payment.getNote();
        Double total = payment.getRefund() == REFUND ? payment.getTotal() : (payment.getRefund() == CREDIT ? payment.getTotal() : -payment.getTotal());
        AMOUNT = !cancel ? total : -total;
    }

    public UserAccountEntry(UsageEO usage, UserEO user) {
        boolean cancel = !usage.isActive();
        ID = usage.getId();
        USER = new HistoryEntryUser(user);
        TYPE = USAGE;
        DATE = usage.getDateStart();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(usage.isActive() ? "" : "Canceled");
        commentSb.append(usage.isActive() ? "" : " | ");
        commentSb.append(usage.getNote() == null ? "" : usage.getNote());
        COMMENT = commentSb.toString();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(usage.getMachine().getName());
        detailSb.append(" | ");
        detailSb.append(usage.getMinutes());
        detailSb.append("min");
        detailSb.append(" | ");
        detailSb.append(usage.getAdditionalCost());
        detailSb.append(" CHF additional ");
        DETAIL = detailSb.toString();
        AMOUNT = !cancel ? -usage.getTotal() : usage.getTotal();
    }

    public UserAccountEntry(SubscriptionEO subscription, UserEO user) {
        boolean cancel = !subscription.isActive();
        ID = subscription.getId();
        USER = new HistoryEntryUser(user);
        TYPE = SUBSCRIPTION;
        DATE = subscription.getDateSubscription();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(subscription.isActive() ? "" : "Canceled");
        commentSb.append(subscription.isActive() ? "" : " | ");
        commentSb.append(subscription.getComment() == null ? "" : subscription.getComment());
        COMMENT = commentSb.toString();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append("Subscription type : ");
        detailSb.append(subscription.getMembershipType().getName());
        detailSb.append(", duration :");
        detailSb.append(subscription.getDuration());
        detailSb.append(" days");
        DETAIL = detailSb.toString();
        AMOUNT = !cancel ? -subscription.getPrice() : subscription.getPrice();
    }

    public UserAccountEntry(MotionStockEO motionStock, UserEO user) {
        //ALWAYS CORRECTION !
        boolean cancel = !motionStock.isActive();
        ID = motionStock.getId();
        USER = new HistoryEntryUser(user);
        DATE = motionStock.getMotionDate() == null ? new Date() : motionStock.getMotionDate();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(motionStock.isActive() ? "" : "Canceled");
        commentSb.append(motionStock.isActive() ? "" : " | ");
        commentSb.append(motionStock.getIo() == null ? "Erreur" : motionStock.getIo());
        COMMENT = commentSb.toString();
        TYPE = HistoryEntryType.PURCHASE;
        StringBuilder detailSbCor = new StringBuilder();
        detailSbCor.append(motionStock.getSupply().getCode());
        detailSbCor.append(" | ");
        detailSbCor.append(motionStock.getQuantity());
        detailSbCor.append(" ");
        detailSbCor.append(motionStock.getSupply().getSupplyUnity().getLabel());
        DETAIL = detailSbCor.toString();
        Double interAmountCor = -(motionStock.getQuantity() * motionStock.getSupply().getSellingPrice());
        AMOUNT = !cancel ? interAmountCor : -interAmountCor;

    }

    public UserAccountEntry(PurchaseEO purchase, UserEO user) {
        boolean cancel = !purchase.isActive();
        ID = purchase.getId();
        USER = new HistoryEntryUser(user);
        TYPE = PURCHASE;
        DATE = purchase.getPurchaseDate();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(purchase.isActive() ? "" : "Canceled");
        commentSb.append(purchase.isActive() ? "" : " | ");
        commentSb.append(purchase.getNote() == null ? "" : purchase.getNote());
        COMMENT = commentSb.toString();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(purchase.getSupply().getCode());
        detailSb.append(" | ");
        detailSb.append(purchase.getQuantity());
        detailSb.append(" ");
        detailSb.append(purchase.getSupply().getSupplyUnity().getLabel());
        detailSb.append(" | discount = ");
        detailSb.append(purchase.getDiscount());
        detailSb.append(" ");
        detailSb.append(purchase.isDiscountPercent() ? "%" : "CHF");
        DETAIL = detailSb.toString();
        Double interAmount = purchase.getQuantity() > 0 ? purchase.getPurchasePrice() : -purchase.getPurchasePrice();
        AMOUNT = !cancel ? -interAmount : interAmount;
    }

    @Override
    public int compareTo(UserAccountEntry o) {
        int res = -this.DATE.compareTo(o.getDATE());
        return res == 0 ? Integer.compare(this.ID, o.getID()) : res;
    }
}
