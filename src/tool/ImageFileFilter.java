package tool;

import java.io.File;

public class ImageFileFilter extends javax.swing.filechooser.FileFilter {
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = getExtension(f);
		if (extension != null) {
			if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Image files";
	}

	private String getExtension(File f) {
		String extension = null;
		String name = f.getName();
		int dotIndex = name.lastIndexOf(".");
		if (dotIndex > 0 && dotIndex < name.length() - 1) {
			extension = name.substring(dotIndex + 1).toLowerCase();
		}
		return extension;

	}
}
