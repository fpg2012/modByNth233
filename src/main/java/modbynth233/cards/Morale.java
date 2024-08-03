package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PenNibPower;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Morale extends MyBaseCard {
    public static final String ID = makeID(Morale.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 0;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Morale() {
        super(ID, info);
        setCostUpgrade(UPG_COST);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PenNibPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Morale();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
