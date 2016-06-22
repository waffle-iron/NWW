/**
 * 
 */
package org.nww.services.files;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.nww.modules.files.orm.FileInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author mga
 *
 */
public class ImageFileServiceImpl implements ImageFileService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final List<String> supportedImages = Arrays.asList(new String[] {"image/jpeg", "image/png"});
	
	/*
	 * (non-Javadoc)
	 * @see org.nww.services.files.ImageFileService#isImage(java.io.File)
	 */
	@Override
	public boolean isImage(File f) {
		try {
			ImageIO.read(f); // check whether the image handler could read the file
			return true;
		} catch (IOException e) {
			log.debug("Checked file " + f.getAbsolutePath() + " to be an image but failed: ", e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.services.files.ImageFileService#getImageType(java.io.File)
	 */
	@Override
	public int getImageType(File f) {
		try {
			BufferedImage i = ImageIO.read(f);
			return getType(i);
		} catch (IOException e) {
			log.debug("Tried to get image type for file " + f.getAbsolutePath() + " but failed: ", e);
		}
		return -1;
	}

	private int getType(BufferedImage i) {
		return i.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : i.getType();
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.services.files.ImageFileService#getImageFromFile(java.io.File)
	 */
	@Override
	public BufferedImage getImageFromFile(File f) throws IOException {
		return ImageIO.read(f);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.services.files.ImageFileService#isLandscapeImage(java.awt.image.BufferedImage)
	 */
	@Override
	public boolean isLandscapeImage(BufferedImage i) {
		return i.getWidth() > i.getHeight();
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.services.files.ImageFileService#resizeImage(java.awt.image.BufferedImage, int, int)
	 */
	@Override
	public BufferedImage resizeImage(BufferedImage i, int width, int height) {
		return resize(i, width, height);
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.services.files.ImageFileService#resizeImage(java.awt.image.BufferedImage, int)
	 */
	@Override
	public BufferedImage resizeImage(BufferedImage i, int dimension) {
		int scaleRef = isLandscapeImage(i) ? i.getWidth() : i.getHeight();
		double scale = dimension / (double)scaleRef;
		
		return resize(i, (int)(i.getWidth() * scale), (int)(i.getHeight() * scale));
	}

	/*
	 * (non-Javadoc)
	 * @see org.nww.services.files.ImageFileService#isImageFile(org.nww.modules.files.orm.FileInformation)
	 */
	@Override
	public boolean isImageFile(FileInformation fi) {
		return !StringUtils.isEmpty(fi.getContentType()) && ImageFileServiceImpl.supportedImages.contains(fi.getContentType());
	}
	
	private BufferedImage resize(BufferedImage original, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, getType(original));
		
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(original, 0, 0, width, height, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		return resizedImage;
	}
}
