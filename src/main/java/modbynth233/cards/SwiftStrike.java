package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class SwiftStrike extends BaseCard {
    public static final String ID = makeID(SwiftStrike.class.getSimpleName());
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 0;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );

    public SwiftStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CardTags.STRIKE);
        upgradeDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL), true));
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