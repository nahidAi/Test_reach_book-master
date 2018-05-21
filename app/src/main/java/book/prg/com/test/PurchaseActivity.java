package book.prg.com.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import book.prg.com.test.util.IabHelper;
import book.prg.com.test.util.IabResult;
import book.prg.com.test.util.Inventory;
import book.prg.com.test.util.Purchase;

public class PurchaseActivity extends AppCompatActivity implements IabHelper.OnIabSetupFinishedListener,
        IabHelper.QueryInventoryFinishedListener{
    IabHelper iabHelper;
    private static final String BOOK_TEST_Premium = "bookTest_premium";
    Button purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        purchase = (Button) findViewById(R.id.button);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchasePremiumAccount();
            }
        });
        checkMyAccount();
    }

    @Override
    public void onIabSetupFinished(IabResult result) {

        if (result.isSuccess()) {
            List<String> details = new ArrayList<>();
            details.add(BOOK_TEST_Premium);
            iabHelper.queryInventoryAsync(true, details, this);
        } else {
            Toast.makeText(this, " ارتباط برقرار نشد...", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onQueryInventoryFinished(IabResult result, Inventory inv) {
        if (result.isSuccess()) {
            Purchase purchase = inv.getPurchase(BOOK_TEST_Premium);
            if (purchase != null) {
                changeToPremiumAccant();

            } else {
                Toast.makeText(this, "خریدی صورت نگرفته است", Toast.LENGTH_SHORT).show();
            }


        }

    }
    public void checkMyAccount() {
        iabHelper = new IabHelper(this, "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwD+o7/lKkBaVXF8jJogrtlVcssVO4dj6uSD/5qPASuywL+Ffvxg7kq1PxSvtLJ2a1V1dGeqTGZk5MOcw0im8lDKamYaKsqIOQMjEZMQ7jGNSB67GeePfr0rj1K1teGxadTj5xfw/GEMYtldTRfUJci5QI1pXgPbDkRYSPGJOeOHIyyvSqajVGH4WRspx/UhAoaxSvhUK/9Avr/MFf2j4QUxUXkyhB9Ccb6u/Zt10m8CAwEAAQ==");
        iabHelper.startSetup(this);
    }

    public void changeToPremiumAccant() {
        purchase.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iabHelper != null) {
            iabHelper.dispose();
            iabHelper = null;
        }
    }
    public void purchasePremiumAccount() {
        iabHelper.launchPurchaseFlow(this, BOOK_TEST_Premium, 101, new IabHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(IabResult result, Purchase info) {
                if (result.isSuccess()) {
                    if (info != null) {
                        changeToPremiumAccant();
                    } else {
                        Toast.makeText(PurchaseActivity.this, " متاسفانه خرید انجام نشد", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101) {
            iabHelper.handleActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
