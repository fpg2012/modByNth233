package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import modbynth233.ModByNth233;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Whip extends MyBaseCard {
    public static final String ID = makeID(Whip.class.getSimpleName());
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = -1;
    private static final int UPG_COST = 1;
    
    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    public Whip() {
        super(ID, info);
        this.portraitImg = null;
        this.portrait = ModByNth233.cardAtlas.findRegion("purple/attack/sash_whip");
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo.DamageType type = DamageInfo.DamageType.NORMAL;
        if (upgraded) {
            type = DamageInfo.DamageType.HP_LOSS;
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, type)));

        int amount = 0;
        for (int i = 0; i < m.powers.size(); i++) {
            if (m.powers.get(i).getClass().equals(ConstrictedPower.class)) {
                amount = m.powers.get(i).amount;
                break;
            }
        }

        if (amount > 0) {
            addToBot(new DamageAction(m, new DamageInfo(p, amount, type)));
            addToBot(new WaitAction(0.1F));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Whip();
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }
}
