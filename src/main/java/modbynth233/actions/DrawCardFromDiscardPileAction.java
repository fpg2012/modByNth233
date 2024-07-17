package modbynth233.actions;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DrawCardFromDiscardPileAction extends AbstractGameAction {
    private boolean shuffleCheck;
    private static final Logger logger = LogManager.getLogger(DrawCardAction.class.getName());
    public static ArrayList<AbstractCard> drawnCards = new ArrayList();
    private boolean clearDrawHistory;
    private AbstractGameAction followUpAction;

    public DrawCardFromDiscardPileAction(AbstractCreature source, int amount, boolean endTurnDraw) {
        this.shuffleCheck = false;
        this.clearDrawHistory = true;
        this.followUpAction = null;
        if (endTurnDraw) {
            AbstractDungeon.topLevelEffects.add(new PlayerTurnEffect());
        }

        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.DRAW;
        if (Settings.FAST_MODE) {
            this.duration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = Settings.ACTION_DUR_FASTER;
        }

    }

    public DrawCardFromDiscardPileAction(AbstractCreature source, int amount) {
        this(source, amount, false);
    }

    public DrawCardFromDiscardPileAction(int amount, boolean clearDrawHistory) {
        this(amount);
        this.clearDrawHistory = clearDrawHistory;
    }

    public DrawCardFromDiscardPileAction(int amount) {
        this((AbstractCreature)null, amount, false);
    }

    public DrawCardFromDiscardPileAction(int amount, AbstractGameAction action) {
        this(amount, action, true);
    }

    public DrawCardFromDiscardPileAction(int amount, AbstractGameAction action, boolean clearDrawHistory) {
        this(amount, clearDrawHistory);
        this.followUpAction = action;
    }

    public void update() {
        if (this.clearDrawHistory) {
            this.clearDrawHistory = false;
            drawnCards.clear();
        }

        if (AbstractDungeon.player.hasPower("No Draw")) {
            AbstractDungeon.player.getPower("No Draw").flash();
            this.endActionWithFollowUp();
        } else if (this.amount <= 0) {
            this.endActionWithFollowUp();
        } else {
            int deckSize = AbstractDungeon.player.drawPile.size();
            int discardSize = AbstractDungeon.player.discardPile.size();
            if (!SoulGroup.isActive()) {
                if (discardSize == 0) {
                    this.endActionWithFollowUp();
                } else if (AbstractDungeon.player.hand.size() == 10) {
                    AbstractDungeon.player.createHandIsFullDialog();
                    this.endActionWithFollowUp();
                } else {
                    if (!this.shuffleCheck) {
                        int tmp;
                        if (this.amount + AbstractDungeon.player.hand.size() > 10) {
                            tmp = 10 - (this.amount + AbstractDungeon.player.hand.size());
                            this.amount += tmp;
                            AbstractDungeon.player.createHandIsFullDialog();
                        }

                        if (this.amount > discardSize) {
                            this.amount = discardSize;
//                            tmp = this.amount - deckSize;
//                            this.addToTop(new DrawCardAction(tmp, this.followUpAction, false));
//                            this.addToTop(new EmptyDeckShuffleAction());
//                            if (deckSize != 0) {
//                                this.addToTop(new DrawCardAction(deckSize, false));
//                            }
//
//                            this.amount = 0;
//                            this.isDone = true;
//                            return;
                        }

                        this.shuffleCheck = true;
                    }

                    this.duration -= Gdx.graphics.getDeltaTime();
                    if (this.amount != 0 && this.duration < 0.0F) {
                        if (Settings.FAST_MODE) {
                            this.duration = Settings.ACTION_DUR_XFAST;
                        } else {
                            this.duration = Settings.ACTION_DUR_FASTER;
                        }

                        --this.amount;
                        if (!AbstractDungeon.player.drawPile.isEmpty()) {
                            AbstractPlayer player = AbstractDungeon.player;
                            drawnCards.add(player.discardPile.getTopCard());

                            if (player.drawPile.isEmpty()) {
                                logger.info("ERROR: How did this happen? No cards in draw pile?? Player.java");
                            } else {
                                AbstractCard c = player.discardPile.getTopCard();
                                c.current_x = CardGroup.DISCARD_PILE_X;
                                c.current_y = CardGroup.DISCARD_PILE_Y;
                                c.setAngle(0.0F, true);
                                c.lighten(false);
                                c.drawScale = 0.12F;
                                c.targetDrawScale = 0.75F;
                                c.triggerWhenDrawn();
                                player.hand.addToHand(c);
                                player.discardPile.removeTopCard();
                                Iterator var4 = player.powers.iterator();

                                while(var4.hasNext()) {
                                    AbstractPower p = (AbstractPower)var4.next();
                                    p.onCardDraw(c);
                                }

                                var4 = player.relics.iterator();

                                while(var4.hasNext()) {
                                    AbstractRelic r = (AbstractRelic)var4.next();
                                    r.onCardDraw(c);
                                }
                            }

                            player.hand.refreshHandLayout();
                        } else {
                            logger.warn("Player attempted to draw from an empty discardpile mid-DrawCardFromDiscardPileAction?MASTER DECK: " + AbstractDungeon.player.masterDeck.getCardNames());
                            this.endActionWithFollowUp();
                        }

                        if (this.amount == 0) {
                            this.endActionWithFollowUp();
                        }
                    }

                }
            }
        }
    }

    private void endActionWithFollowUp() {
        this.isDone = true;
        if (this.followUpAction != null) {
            this.addToTop(this.followUpAction);
        }

    }
}
