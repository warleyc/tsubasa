/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSellCardCoinDetailComponent } from 'app/entities/m-sell-card-coin/m-sell-card-coin-detail.component';
import { MSellCardCoin } from 'app/shared/model/m-sell-card-coin.model';

describe('Component Tests', () => {
  describe('MSellCardCoin Management Detail Component', () => {
    let comp: MSellCardCoinDetailComponent;
    let fixture: ComponentFixture<MSellCardCoinDetailComponent>;
    const route = ({ data: of({ mSellCardCoin: new MSellCardCoin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSellCardCoinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MSellCardCoinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSellCardCoinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mSellCardCoin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
