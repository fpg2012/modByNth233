package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Guerrilla extends MyBaseCard {
    public static final String ID = makeID(Guerrilla.class.getSimpleName());
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 1;
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 1;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );

    public Guerrilla() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        upgradeMagic = true;
        upgradeBlock = true;
        upgradeDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.getIntentBaseDmg() >= 0) {
            for (int i = 0; i < this.magicNumber; i++) {
                addToBot(new GainBlockAction(p, this.block));
                addToBot(new WaitAction(0.1F));
            }
        } else {
            for (int i = 0; i < this.magicNumber; i++) {
                addToBot(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.FIRE));
                addToBot(new WaitAction(0.1F));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Guerrilla();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
