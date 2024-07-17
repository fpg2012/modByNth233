package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Dodge extends BaseCard {
    public static final String ID = makeID(Dodge.class.getSimpleName());
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 2;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPG_MAGIC_NUMBER = 1;

    public Dodge() {
        super(ID, info);
        setBlock(BLOCK);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        upgradeMagic = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 1; i <= magicNumber; i++) {
            addToBot(new GainBlockAction(p, block));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Dodge();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
