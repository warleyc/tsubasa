/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRecommendFormationFilterElementDetailComponent } from 'app/entities/m-recommend-formation-filter-element/m-recommend-formation-filter-element-detail.component';
import { MRecommendFormationFilterElement } from 'app/shared/model/m-recommend-formation-filter-element.model';

describe('Component Tests', () => {
  describe('MRecommendFormationFilterElement Management Detail Component', () => {
    let comp: MRecommendFormationFilterElementDetailComponent;
    let fixture: ComponentFixture<MRecommendFormationFilterElementDetailComponent>;
    const route = ({ data: of({ mRecommendFormationFilterElement: new MRecommendFormationFilterElement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRecommendFormationFilterElementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MRecommendFormationFilterElementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRecommendFormationFilterElementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mRecommendFormationFilterElement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
