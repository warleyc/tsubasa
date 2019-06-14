/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MSituationDetailComponent } from 'app/entities/m-situation/m-situation-detail.component';
import { MSituation } from 'app/shared/model/m-situation.model';

describe('Component Tests', () => {
  describe('MSituation Management Detail Component', () => {
    let comp: MSituationDetailComponent;
    let fixture: ComponentFixture<MSituationDetailComponent>;
    const route = ({ data: of({ mSituation: new MSituation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MSituationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MSituationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MSituationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mSituation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
