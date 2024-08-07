package modbynth233.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import modbynth233.ModByNth233;
import modbynth233.util.CardStats;

import java.util.Hashtable;

public abstract class MyBaseCard extends BaseCard {
    protected static int counter = 0;
    public static final int WIDTH = 250;
    public static final int HEIGHT = 190;
    public static final Hashtable<String, Integer> portraitRegionMap = new Hashtable<>();

    protected TextureAtlas.AtlasRegion portraitRegion;
    protected Texture largePortraitImg = null;

    private static TextureRegion attackRegion, skillRegion, powerRegion;
    private static Texture attackMaskImage, skillMaskImage, powerMaskImage;
    protected Texture maskedTexture;
    protected TextureAtlas.AtlasRegion maskedTextureRegion;

    public MyBaseCard(String ID, CardStats info) {
        super(ID, info);
        int count = 0;
        if (portraitRegionMap.get(ID) != null) {
            count = portraitRegionMap.get(ID);
        } else {
            count = counter;
            counter++;
        }
        int i = count / 8, j = count % 8;
        this.portraitRegion = new TextureAtlas.AtlasRegion(ModByNth233.cardTexture, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
        portraitRegionMap.put(ID, count);
        this.portrait = portraitRegion;
//        applyMask(info);
    }

    public MyBaseCard(String ID, CardStats info, int i, int j) {
        super(ID, info);
        this.portraitRegion = new TextureAtlas.AtlasRegion(ModByNth233.cardTexture2, j * WIDTH, i * HEIGHT, WIDTH, HEIGHT);
        this.portrait = portraitRegion;
    }

    protected void applyMask(CardStats info) {
        if (attackRegion == null) {
            attackMaskImage = new Texture(Gdx.files.internal("modbynth233/images/cards/attack/mask.png"));
            skillMaskImage = new Texture(Gdx.files.internal("modbynth233/images/cards/skill/mask.png"));
            powerMaskImage = new Texture(Gdx.files.internal("modbynth233/images/cards/power/mask.png"));

            attackRegion = new TextureRegion(attackMaskImage);
            skillRegion = new TextureRegion(skillMaskImage);
            powerRegion = new TextureRegion(powerMaskImage);
        }
        TextureRegion mask = null;
        switch (info.cardType) {
            case ATTACK:
                mask = attackRegion;
                break;
            case POWER:
                mask = powerRegion;
                break;
            default:
                mask = skillRegion;
        }
        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, WIDTH, HEIGHT, false);
        SpriteBatch sb = new SpriteBatch();
        fbo.begin();
        sb.begin();

        Gdx.gl.glColorMask(false, false, false, true);
        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        sb.draw(mask, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_ALPHA);
        sb.draw(this.portraitRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.flush();

        Gdx.gl.glColorMask(true, true, true, true);
        sb.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);
        sb.draw(this.portraitRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.flush();

        sb.end();
        fbo.end();
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGBA8888);
        fbo.getColorBufferTexture().draw(pixmap, 0, 0);
        maskedTexture = new Texture(pixmap);
        maskedTextureRegion = new TextureAtlas.AtlasRegion(maskedTexture, 0, 0, maskedTexture.getWidth(), maskedTexture.getHeight());
        this.portrait = maskedTextureRegion;
    }

    protected void loadLargePortraitImage() {
        TextureAtlas.AtlasRegion portraitRegionFlipped = portraitRegion;
        portraitRegionFlipped.flip(false, true);
        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, WIDTH*2, HEIGHT*2, false);
        SpriteBatch sb = new SpriteBatch();
        fbo.begin();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        sb.draw(portraitRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
        fbo.end();
        this.largePortraitImg = fbo.getColorBufferTexture();
    }

    @Override
    protected Texture getPortraitImage() {
        if (this.largePortraitImg == null) {
            loadLargePortraitImage();
        }
        return this.largePortraitImg;
    }
}
