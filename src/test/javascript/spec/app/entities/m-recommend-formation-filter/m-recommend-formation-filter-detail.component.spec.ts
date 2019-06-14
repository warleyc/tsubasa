/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRecommendFormationFilterDetailComponent } from 'app/entities/m-recommend-formation-filter/m-recommend-formation-filter-detail.component';
import { MRecommendFormationFilter } from 'app/shared/model/m-recommend-formation-filter.model';

describe('Component Tests', () => {
  describe('MRecommendFormationFilter Management Detail Component', () => {
    let comp: MRecommendFormationFilterDetailComponent;
    let fixture: ComponentFixture<MRecommendFormationFilterDetailComponent>;
    const route = ({ data: of({ mRecommendFormationFilter: new MRecommendFormationFilter(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRecommendFormationFilterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MRecommendFormationFilterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRecommendFormationFilterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mRecommendFormationFilter).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
