/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRecommendShopMessageDetailComponent } from 'app/entities/m-recommend-shop-message/m-recommend-shop-message-detail.component';
import { MRecommendShopMessage } from 'app/shared/model/m-recommend-shop-message.model';

describe('Component Tests', () => {
  describe('MRecommendShopMessage Management Detail Component', () => {
    let comp: MRecommendShopMessageDetailComponent;
    let fixture: ComponentFixture<MRecommendShopMessageDetailComponent>;
    const route = ({ data: of({ mRecommendShopMessage: new MRecommendShopMessage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRecommendShopMessageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MRecommendShopMessageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRecommendShopMessageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mRecommendShopMessage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
