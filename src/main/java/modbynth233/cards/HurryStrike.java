package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class HurryStrike extends MyBaseCard {
    public static final String ID = makeID(HurryStrike.class.getSimpleName());
    private static final int DAMAGE = 10;
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
            CardTarget.ALL_ENEMY,
            1
    );

    public HurryStrike() {
        super(ID, info, 1, 8);
        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CardTags.STRIKE);
        upgradeDamage = true;
    }

    public HurryStrike(int realDamage) {
        super(ID, info, 1, 8);
        setDamage(realDamage, UPG_DAMAGE);
        tags.add(CardTags.STRIKE);
        upgradeDamage = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new WaitAction(0.2F));
        addToBot(new ExhaustAction(1, true, false, false));

        randomizeDamage(upgraded);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HurryStrike(this.damage);
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

//    @Override
//    public void triggerWhenDrawn() {
//        super.triggerWhenDrawn();
//        randomizeDamage(!upgraded);
//    }

    private void randomizeDamage(boolean resetBaseDamageFlag) {
        int rnd = AbstractDungeon.cardRandomRng.random(-1, 4);
        if (resetBaseDamageFlag) {
            setDamage(this.damage + rnd);
        } else {
            setDamage(this.baseDamage + rnd);
        }
    }

}
