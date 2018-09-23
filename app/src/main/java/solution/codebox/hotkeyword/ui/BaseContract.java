package solution.codebox.hotkeyword.ui;

import android.support.annotation.NonNull;

public interface BaseContract {
    interface View<P extends Presenter> {
        void bindPresenter(@NonNull P presenter);

        void unbindPresenter();
    }

    interface Presenter<V> {
        void attachView(@NonNull V view);

        void detachView();
    }
}
