/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MStoreReviewUrlDetailComponent } from 'app/entities/m-store-review-url/m-store-review-url-detail.component';
import { MStoreReviewUrl } from 'app/shared/model/m-store-review-url.model';

describe('Component Tests', () => {
  describe('MStoreReviewUrl Management Detail Component', () => {
    let comp: MStoreReviewUrlDetailComponent;
    let fixture: ComponentFixture<MStoreReviewUrlDetailComponent>;
    const route = ({ data: of({ mStoreReviewUrl: new MStoreReviewUrl(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStoreReviewUrlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MStoreReviewUrlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MStoreReviewUrlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mStoreReviewUrl).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
