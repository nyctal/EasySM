package boundaries;

import datatypes.Report;
import store.*;

public interface BaseBoundary{
	public void ko(Report r);
	public void ok(Report r);
	public void close();
	public void validate(Element el);
}
