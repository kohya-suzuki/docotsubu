package model;

import java.util.List;

public class PostMutterLogic {
	public void execute(Mutter mutter, List<Mutter> mutterList) {
		mutterList.add(0, mutter); // "0"という指定で、右のmutterというエレメントをlistの先頭に追加 解説①
	}
}