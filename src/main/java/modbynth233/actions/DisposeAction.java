package modbynth233.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import java.util.Iterator;

public class DisposeAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private int additional_amount = 1;
    private boolean randomExhaust = true;

    public DisposeAction(int additional_amount, boolean randomExhaust) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.additional_amount = additional_amount;
        this.randomExhaust = randomExhaust;
    }

    public void update() {
        if (this.randomExhaust) {
            if (!this.p.hand.isEmpty()) {
                AbstractCard card = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                this.p.hand.moveToExhaustPile(card);
                this.addToTop(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, card.costForTurn + this.additional_amount)));
            }
            this.isDone = true;
        } else {
            if (this.duration == Settings.ACTION_DUR_FAST) {
                if (this.p.hand.isEmpty()) {
                    this.isDone = true;
                } else if (this.p.hand.size() == 1) {
                    if (this.p.hand.getBottomCard().costForTurn == -1) {
                        this.addToTop(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, EnergyPanel.getCurrentEnergy() + this.additional_amount)));
                    } else if (this.p.hand.getBottomCard().costForTurn > 0) {
                        this.addToTop(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, this.p.hand.getBottomCard().costForTurn + this.additional_amount)));
                    }

                    this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                    this.tickDuration();
                } else {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
                    this.tickDuration();
                }
            } else {
                if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                    AbstractCard c;
                    for (Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); this.p.hand.moveToExhaustPile(c)) {
                        c = (AbstractCard) var1.next();
                        if (c.costForTurn == -1) {
                            this.addToTop(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, EnergyPanel.getCurrentEnergy() + this.additional_amount)));
                        } else if (c.costForTurn > 0) {
                            this.addToTop(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, this.p.hand.getBottomCard().costForTurn + this.additional_amount)));
                        }
                    }

                    AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                    AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                }

                this.tickDuration();
            }
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("modByNth233:DisposeAction");
        TEXT = uiStrings.TEXT;
    }
}
