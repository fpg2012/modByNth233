package modbynth233.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.actions.PreparationAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Preparation extends MyBaseCard {
    public static final String ID = makeID(Preparation.class.getSimpleName());
//    private static final int DAMAGE = 0;
//    private static final int UPG_DAMAGE = 0;
//    private static final int BLOCK = 0;
//    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPG_MAGIC_NUMBER = 1;
//    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -1
    );

    public Preparation() {
        super(ID, info, 3, 0);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        upgradeMagic = true;
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PreparationAction(p, this.freeToPlayOnce, this.energyOnUse, this.magicNumber, 0));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Preparation();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}

