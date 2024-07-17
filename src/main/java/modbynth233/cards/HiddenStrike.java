package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.actions.HiddenStrikeAction;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class HiddenStrike extends BaseCard {
    public static final String ID = makeID(HiddenStrike.class.getSimpleName());
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 5;

    private static final int MAGIC_NUMBER = 2;
    private static final int UPG_MAGIC_NUMBER = 1;

    public HiddenStrike() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        tags.add(CardTags.STRIKE);
        upgradedMagicNumber = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL);
        addToBot(new HiddenStrikeAction(m, info, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HiddenStrike();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
