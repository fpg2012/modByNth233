package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class StrongStrike extends BaseCard {
    public static final String ID = makeID(StrongStrike.class.getSimpleName());
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 1000;
    private static final int UPG_DAMAGE = 1000;

    public StrongStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setInnate(false, true);
        setCostUpgrade(0);
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
        upgradeDamage = true;
        upgradeCost = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new TalkAction(true, cardStrings.EXTENDED_DESCRIPTION[0], 2, 2));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrongStrike();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
