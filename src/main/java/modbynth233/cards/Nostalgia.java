package modbynth233.cards;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import modbynth233.ModByNth233;
import modbynth233.actions.NostalgiaAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

import javax.swing.*;

public class Nostalgia extends MyBaseCard {
    public static final String ID = makeID(Nostalgia.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.CURSE,
            CardRarity.CURSE,
            CardTarget.SELF,
            2
    );

    public Nostalgia() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 0;
        for (int i = 0; i < p.powers.size(); i++) {
            if (p.powers.get(i).getClass().equals(DexterityPower.class)) {
                amount = p.powers.get(i).amount;
                break;
            }
        }

        if (amount > 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, amount), amount));
        }
    }

    @Override
    public void onRemoveFromMasterDeck() {
        NostalgiaAction action = new NostalgiaAction();
        AbstractDungeon.actionManager.addToNextCombat(action);
        super.onRemoveFromMasterDeck();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Nostalgia();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

}
