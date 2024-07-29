package modbynth233.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.InfernalBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modbynth233.character.Tinclad;
import modbynth233.util.CardStats;

public class Innovation extends MyBaseCard {
    public static final String ID = makeID(Innovation.class.getSimpleName());
    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 0;
    private static final int UPG_COST = 1;

    public static final CardStats info = new CardStats(
            Tinclad.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1
    );

    public Innovation() {
        super(ID, info);
        setMagic(MAGIC_NUMBER);
        setCostUpgrade(UPG_COST);
        upgradeMagic = true;
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustAction(1, false, false, false));
        addToBot(new WaitAction(0.1F));

        int random = AbstractDungeon.cardRandomRng.random(9);
        CardType type = CardType.ATTACK;
        if (random < 3) {
            type = CardType.ATTACK;
        } else if (random >= 3 && random < 6) {
            type = CardType.SKILL;
        } else if (random >= 6) {
            type = CardType.POWER;
        }

        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(type).makeCopy();
//        c.setCostForTurn(0);
        this.addToBot(new MakeTempCardInHandAction(c, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Innovation();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        setExhaust(false);
    }
}
