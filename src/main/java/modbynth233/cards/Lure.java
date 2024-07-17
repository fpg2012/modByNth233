package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Lure extends BaseCard{
    public static final String ID = makeID(Lure.class.getSimpleName());
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    private static final int BLOCK = 7;
    private static final int MAGIC_NUMBER = 4;

    public Lure() {
        super(ID, info);
        setBlock(BLOCK);
        setMagic(MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new GainBlockAction(m, MAGIC_NUMBER));
        if (upgraded) {
            addToBot(new DrawCardAction(1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Lure();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
