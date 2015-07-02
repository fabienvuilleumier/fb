package net.collaud.fablab.api.data.virtual;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.api.data.MotionStockEO;
import net.collaud.fablab.api.data.PurchaseEO;
import net.collaud.fablab.api.data.RevisionEO;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserPaymentEO;
import net.collaud.fablab.api.data.type.HistoryEntryType;
import static net.collaud.fablab.api.data.type.RefundAction.CREDIT;
import static net.collaud.fablab.api.data.type.RefundAction.REFUND;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.CAISSE_POSTE_BANQUE;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.CREANCES_CLIENT;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.DETTES_FOURNISSEUR;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.ENTRETIEN_MACHINE;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.HONORAIRES;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.PRODUIT_EXCEPTIONNEL;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.COTISATIONS;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.STOCK_DIVERS;
import static net.collaud.fablab.api.data.virtual.HistoryEntryAccounts.VENTES_MARCHANDISES;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"ID", "TYPE"})
public class HistoryEntry implements Comparable<HistoryEntry> {

    private final int ID;
    private final HistoryEntryType TYPE;
    private final String COMMENT;
    private final Date DATE;
    private final double AMOUNT;
    private final String DETAIL;
    private final HistoryEntryUser USER;
    private final HistoryEntryAccounts ACCOUNT_CREDIT;
    private final HistoryEntryAccounts ACCOUNT_DEBIT;

    public HistoryEntry(UserPaymentEO payment) {
        boolean cancel = !payment.isActive();
        boolean forTheLab = payment.isPayedForFabLab();
        boolean cash = payment.getCashier() != null;
        boolean refund = payment.getRefund() == REFUND || payment.getRefund() == CREDIT;
        TYPE = HistoryEntryType.PAYMENT;
        ID = payment.getId();
        DATE = payment.getDatePayment();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(payment.isActive() ? "" : "Canceled");
        commentSb.append(payment.isActive() ? "" : " | ");
        commentSb.append(payment.getLabel());
        commentSb.append(" | ");
        commentSb.append(payment.getNote() == null ? "" : payment.getNote());
        COMMENT = commentSb.toString();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(cash ? "cashier=" + payment.getCashier().getFirstLastName() : "Use it's credit");
        detailSb.append(" | ");
        detailSb.append(payment.isPayedForFabLab()? "Payed for the lab" : "Payed for the user ");
        DETAIL = detailSb.toString();
        double interAmount = forTheLab ? -payment.getTotal() : payment.getTotal();
        AMOUNT = !cancel ? interAmount : -interAmount;
        USER = new HistoryEntryUser(payment.getUser());
        if (!cancel) {
            if (forTheLab) {
                ACCOUNT_CREDIT = payment.getAccountCredit();
                ACCOUNT_DEBIT = payment.getAccountDebit();
            } else {
                if (refund) {
                    if (cash) {
                        ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                        ACCOUNT_DEBIT = CREANCES_CLIENT;
                    } else {
                        //don't possible
                        ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                        ACCOUNT_DEBIT = CREANCES_CLIENT;
                    }
                } else {
                    if (cash) {
                        ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                        ACCOUNT_DEBIT = PRODUIT_EXCEPTIONNEL;
                    } else {
                        ACCOUNT_CREDIT = CREANCES_CLIENT;
                        ACCOUNT_DEBIT = PRODUIT_EXCEPTIONNEL;
                    }
                }
            }
        } else {
            if (forTheLab) {
                ACCOUNT_CREDIT = payment.getAccountDebit();
                ACCOUNT_DEBIT = payment.getAccountCredit();
            } else {
                if (!refund) {
                    if (cash) {
                        ACCOUNT_CREDIT = PRODUIT_EXCEPTIONNEL;
                        ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
                    } else {
                        ACCOUNT_CREDIT = PRODUIT_EXCEPTIONNEL;
                        ACCOUNT_DEBIT = CREANCES_CLIENT;
                    }
                } else {
                    if (cash) {
                        ACCOUNT_CREDIT = CREANCES_CLIENT;
                        ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
                    } else {
                        //don't possible
                        ACCOUNT_CREDIT = CREANCES_CLIENT;
                        ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
                    }
                }
            }
        }
    }

    public HistoryEntry(UsageEO usage) {
        boolean cancel = !usage.isActive();
        boolean cash = usage.getCashier() != null;
        TYPE = HistoryEntryType.USAGE;
        ID = usage.getId();
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
        detailSb.append(" CHF additional | ");
        detailSb.append(cash ? "cashier=" + usage.getCashier().getFirstLastName() : "Use it's credit");
        DETAIL = detailSb.toString();
        double interAmount = usage.getTotal();
        AMOUNT = !cancel ? interAmount : -interAmount;
        USER = new HistoryEntryUser(usage.getUser());
        if (!cancel) {
            if (cash) {
                ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                ACCOUNT_DEBIT = HONORAIRES;
            } else {
                ACCOUNT_CREDIT = CREANCES_CLIENT;
                ACCOUNT_DEBIT = HONORAIRES;
            }
        } else {
            if (cash) {
                ACCOUNT_CREDIT = HONORAIRES;
                ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
            } else {
                ACCOUNT_CREDIT = HONORAIRES;
                ACCOUNT_DEBIT = CREANCES_CLIENT;
            }
        }

    }

    public HistoryEntry(RevisionEO revision) {
        boolean cancel = !revision.isActive();
        TYPE = HistoryEntryType.REVISION;
        ID = revision.getId();
        DATE = revision.getRevisionDate();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(revision.isActive() ? "" : "Canceled");
        commentSb.append(revision.isActive() ? "" : " | ");
        commentSb.append(revision.getNote() == null ? "" : revision.getNote());
        COMMENT = commentSb.toString();
        DETAIL = revision.getMachine().getName();
        double interAmount = -revision.getCost();
        AMOUNT = !cancel ? interAmount : -interAmount;
        USER = new HistoryEntryUser(revision.getUser());
        if (!cancel) {
            ACCOUNT_CREDIT = ENTRETIEN_MACHINE;
            ACCOUNT_DEBIT = DETTES_FOURNISSEUR;
        } else {
            ACCOUNT_CREDIT = DETTES_FOURNISSEUR;
            ACCOUNT_DEBIT = ENTRETIEN_MACHINE;
        }

    }

    public HistoryEntry(SubscriptionEO subscription) {
        boolean cancel = !subscription.isActive();
        TYPE = HistoryEntryType.SUBSCRIPTION;
        ID = subscription.getId();
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
        double interAmount = -subscription.getPrice();
        AMOUNT = !cancel ? interAmount : -interAmount;
        USER = new HistoryEntryUser(subscription.getUser());
        if (!cancel) {
            ACCOUNT_CREDIT = CREANCES_CLIENT;
            ACCOUNT_DEBIT = COTISATIONS;
        } else {
            ACCOUNT_CREDIT = COTISATIONS;
            ACCOUNT_DEBIT = CREANCES_CLIENT;
        }

    }

    public HistoryEntry(MotionStockEO motionStock) {
        boolean cancel = !motionStock.isActive();
        ID = motionStock.getId() == null ? 0 : motionStock.getId();
        DATE = motionStock.getMotionDate() == null ? new Date() : motionStock.getMotionDate();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(motionStock.isActive() ? "" : "Canceled");
        commentSb.append(motionStock.isActive() ? "" : " | ");
        commentSb.append(motionStock.getIo() == null ? "Erreur" : motionStock.getIo());
        COMMENT = commentSb.toString();
        switch (motionStock.getIo()) {
            case "Entrée":
            case "Entrée [ajout]":
                TYPE = HistoryEntryType.SUPPLY;
                StringBuilder detailSb = new StringBuilder();
                detailSb.append(motionStock.getSupply().getCode());
                detailSb.append(" | ");
                detailSb.append(motionStock.getQuantity());
                detailSb.append(" ");
                detailSb.append(motionStock.getSupply().getSupplyUnity().getLabel());
                DETAIL = detailSb.toString();
                double interAmount = -(motionStock.getQuantity() * motionStock.getSupply().getUnityBuyingPrice());
                AMOUNT = !cancel ? interAmount : -interAmount;
                USER = new HistoryEntryUser(motionStock.getUser());
                if (!cancel) {
                    ACCOUNT_CREDIT = STOCK_DIVERS;
                    ACCOUNT_DEBIT = DETTES_FOURNISSEUR;
                } else {
                    ACCOUNT_CREDIT = STOCK_DIVERS;
                    ACCOUNT_DEBIT = DETTES_FOURNISSEUR;
                }

                break;
            case "Correction":
                TYPE = HistoryEntryType.PURCHASE;
                StringBuilder detailSbCor = new StringBuilder();
                detailSbCor.append(motionStock.getSupply().getCode());
                detailSbCor.append(" | ");
                detailSbCor.append(motionStock.getQuantity());
                detailSbCor.append(" ");
                detailSbCor.append(motionStock.getSupply().getSupplyUnity().getLabel());
                DETAIL = detailSbCor.toString();
                double interAmountCor = -(motionStock.getQuantity() * motionStock.getSupply().getSellingPrice());
                AMOUNT = motionStock.isActive() ? interAmountCor : -interAmountCor;
                USER = new HistoryEntryUser(motionStock.getUser());
                ACCOUNT_CREDIT = DETTES_FOURNISSEUR;
                ACCOUNT_DEBIT = STOCK_DIVERS;
                break;
            default:
                TYPE = HistoryEntryType.SUPPLY;
                DETAIL = "Erreur";
                AMOUNT = 0;
                USER = new HistoryEntryUser(null);
                ACCOUNT_CREDIT = STOCK_DIVERS;
                ACCOUNT_DEBIT = DETTES_FOURNISSEUR;
                break;
        }
    }

    public HistoryEntry(PurchaseEO purchase) {
        boolean cancel = !purchase.isActive();
        boolean cash = purchase.getCashier() != null;
        TYPE = HistoryEntryType.PURCHASE;
        ID = purchase.getId();
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
        detailSb.append(" | ");
        detailSb.append(cash ? "cashier=" + purchase.getCashier().getFirstLastName() : "Use it's credit");
        DETAIL = detailSb.toString();
        double interAmount = purchase.getQuantity() > 0 ? purchase.getPurchasePrice() : -purchase.getPurchasePrice();
        AMOUNT = !cancel ? interAmount : -interAmount;
        USER = new HistoryEntryUser(purchase.getUser());
        if (!cancel) {
            if (cash) {
                ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                ACCOUNT_DEBIT = VENTES_MARCHANDISES;
            } else {
                ACCOUNT_CREDIT = CREANCES_CLIENT;
                ACCOUNT_DEBIT = VENTES_MARCHANDISES;
            }
        } else {
            if (cash) {
                ACCOUNT_CREDIT = VENTES_MARCHANDISES;
                ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
            } else {
                ACCOUNT_CREDIT = VENTES_MARCHANDISES;
                ACCOUNT_DEBIT = CREANCES_CLIENT;
            }
        }
    }

    @Override
    public int compareTo(HistoryEntry o) {
        int res = -this.DATE.compareTo(o.getDATE());
        return res == 0 ? Integer.compare(this.ID, o.getID()) : res;
    }

}
