package modbynth233.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;

import java.util.Iterator;

public class AddToExhaustAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private AbstractCard cardToAdd;

    public AddToExhaustAction(AbstractCard cardToAdd) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.cardToAdd = cardToAdd;
    }

    public void update() {
        AbstractDungeon.effectList.add(new ExhaustCardEffect(cardToAdd.makeStatEquivalentCopy()));
        p.exhaustPile.addToTop(cardToAdd.makeStatEquivalentCopy());
        this.isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("modByNth233:DisposeAction");
        TEXT = uiStrings.TEXT;
    }
}
