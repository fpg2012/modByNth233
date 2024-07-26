package modbynth233.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import modbynth233.cards.Flashback;
import modbynth233.cards.Nostalgia;

import java.util.ArrayList;
import java.util.Iterator;

public class RecallAction extends AbstractGameAction {
    private AbstractPlayer p;
    private final boolean upgrade;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private ArrayList<AbstractCard> exhumes = new ArrayList();
    private int numCards = 1;

    public RecallAction(boolean upgrade, int numCards) {
        this.upgrade = upgrade;
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.numCards = numCards;
    }

    public void update() {
        Iterator c;
        AbstractCard derp;
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
            } else if (this.p.exhaustPile.isEmpty()) {
                this.isDone = true;
            } else if (this.p.exhaustPile.size() == 1) {
                AbstractCard card = this.p.exhaustPile.getTopCard();
                card.unfadeOut();
                this.p.hand.addToHand(card);
                if (AbstractDungeon.player.hasPower("Corruption") && card.type == CardType.SKILL) {
                    card.setCostForTurn(-9);
                }

                this.p.exhaustPile.removeCard(card);
                if (this.upgrade && card.canUpgrade()) {
                    card.upgrade();
                }

                card.unhover();
                card.fadingOut = false;
                this.isDone = true;
            } else {
                c = this.p.exhaustPile.group.iterator();

                while(c.hasNext()) {
                    derp = (AbstractCard)c.next();
                    derp.stopGlowing();
                    derp.unhover();
                    derp.unfadeOut();
                }

                c = this.p.exhaustPile.group.iterator();

//                while(c.hasNext()) {
//                    derp = (AbstractCard)c.next();
//                    if (derp.cardID.equals("Exhume")) {
//                        c.remove();
//                        this.exhumes.add(derp);
//                    }
//                }

                AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, this.numCards, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (c = AbstractDungeon.gridSelectScreen.selectedCards.iterator(); c.hasNext(); derp.unhover()) {
                    derp = (AbstractCard)c.next();
                    if (derp.getClass() == Nostalgia.class) {
                        derp.upgrade();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(derp.makeStatEquivalentCopy(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        Iterator<AbstractCard> cardIter =  this.p.masterDeck.group.iterator();
                        ArrayList<AbstractCard> toFind = new ArrayList<>();
                        while (cardIter.hasNext()) {
                            AbstractCard card = cardIter.next();
                            if (card.getClass() == Flashback.class) {
                                toFind.add(card);
                            }
                        }
                        for (int i = 0; i < toFind.size(); ++i) {
                            AbstractCard card = toFind.get(i);
                            p.masterDeck.removeCard(card);
                            AbstractDungeon.effectList.add(new PurgeCardEffect(card));
                        }
                    }
                    this.p.hand.addToHand(derp);
                    if (AbstractDungeon.player.hasPower("Corruption") && derp.type == CardType.SKILL) {
                        derp.setCostForTurn(-9);
                    }

                    this.p.exhaustPile.removeCard(derp);
                    if (this.upgrade && derp.canUpgrade()) {
                        derp.upgrade();
                    }
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
                this.p.exhaustPile.group.addAll(this.exhumes);
                this.exhumes.clear();

                for(c = this.p.exhaustPile.group.iterator(); c.hasNext(); derp.target_y = 0.0F) {
                    derp = (AbstractCard)c.next();
                    derp.unhover();
                    derp.target_x = (float)CardGroup.DISCARD_PILE_X;
                }
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("modByNth233:RecallAction");
        TEXT = uiStrings.TEXT;
    }
}
