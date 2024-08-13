package modbynth233.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class SwiftStrike extends MyBaseCard {
    public static final String ID = makeID(SwiftStrike.class.getSimpleName());
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC_NUMBER = 4;
    private static final int UPG_MAGIC_NUMBER = 2;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public SwiftStrike() {
        super(ID, info, 3, 9);
        setBlock(DAMAGE);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(m, this.block, DamageInfo.DamageType.NORMAL), true));
            addToBot(new WaitAction(0.1F));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwiftStrike();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
