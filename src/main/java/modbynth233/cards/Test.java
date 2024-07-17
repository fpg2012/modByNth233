package modbynth233.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.actions.TestAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Test extends BaseCard {
    public static final String ID = makeID(Test.class.getSimpleName());
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public Test() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC_NUMBER);
        this.cardsToPreview = new Chance();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TestAction(this.damage, this.magicNumber, m, this.cardsToPreview));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Test();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.cardsToPreview.upgrade();
    }
}
