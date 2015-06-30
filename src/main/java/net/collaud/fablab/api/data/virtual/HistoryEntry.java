package net.collaud.fablab.api.data.virtual;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.api.data.PurchaseEO;
import net.collaud.fablab.api.data.RevisionEO;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserPaymentEO;
import net.collaud.fablab.api.data.type.HistoryEntryType;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "type"})
public class HistoryEntry implements Comparable<HistoryEntry> {

    private final int id;
    private final HistoryEntryType type;
    private final String comment;
    private final Date date;
    private final double amount;
    private final String detail;
    private final HistoryEntryUser user;

    public HistoryEntry(UserPaymentEO payment) {
        type = HistoryEntryType.PAYMENT;
        id = payment.getId();
        date = payment.getDatePayment();
        comment = payment.getLabel() + " " + payment.getNote() == null ? "" : payment.getNote();
        detail = (payment.getCashier() == null ? "Use it's credit" : "cashier=" + payment.getCashier().getFirstLastName()) + " | " + (payment.getPayedForFabLab() == null ? "Payed for the user " : (payment.getPayedForFabLab() ? "Payed for the lab" : "Payed for the user "));
        amount = payment.getTotal();
        user = new HistoryEntryUser(payment.getUser());
    }

    public HistoryEntry(UsageEO usage) {
        type = HistoryEntryType.USAGE;
        id = usage.getId();
        date = usage.getDateStart();
        comment = usage.getNote() == null ? "" : usage.getNote();
        detail = usage.getMachine().getName() + " | " + usage.getMinutes() + "min" + " | " + usage.getAdditionalCost() + " CHF additional | " + (usage.getCashier() == null ? "Use it's credit" : "cashier=" + usage.getCashier().getFirstLastName());
        amount = -((usage.getPricePerHour() * usage.getMinutes()) / 60 + usage.getAdditionalCost());
        user = new HistoryEntryUser(usage.getUser());
    }

    public HistoryEntry(PurchaseEO purchase) {
        type = HistoryEntryType.PURCHASE;
        id = purchase.getId();
        date = purchase.getPurchaseDate();
        comment = purchase.getNote() == null ? "" : purchase.getNote();
        detail = purchase.getSupply().getCode() + " | " + purchase.getQuantity() + " " + purchase.getSupply().getSupplyUnity().getLabel() + " | "
                + purchase.getDiscount() + " " + (purchase.isDiscountPercent() ? "%" : "CHF") + " | " + (purchase.getCashier() == null ? "Use it's credit" : "cashier=" + purchase.getCashier().getFirstLastName());
        amount = purchase.getPurchasePrice();
        user = new HistoryEntryUser(purchase.getUser());
    }

    public HistoryEntry(RevisionEO revision) {
        type = HistoryEntryType.REVISION;
        id = revision.getId();
        date = revision.getRevisionDate();
        comment = revision.getNote() == null ? "" : revision.getNote();
        detail = revision.getMachine().getName();
        amount = revision.getCost();
        user = new HistoryEntryUser(revision.getUser());
    }

    public HistoryEntry(SubscriptionEO subscription) {
        type = HistoryEntryType.SUBSCRIPTION;
        id = subscription.getId();
        date = subscription.getDateSubscription();
        comment = subscription.getComment();
        detail = "Subscription type : " + subscription.getMembershipType().getName() + ", duration :" + subscription.getDuration() + " days";
        amount = -subscription.getPrice();
        user = new HistoryEntryUser(subscription.getUser());
    }

    @Override
    public int compareTo(HistoryEntry o) {
        int res = -this.date.compareTo(o.getDate());
        return res == 0 ? Integer.compare(this.id, o.id) : res;
    }

}
