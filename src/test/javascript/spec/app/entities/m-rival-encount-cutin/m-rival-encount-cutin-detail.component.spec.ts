/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRivalEncountCutinDetailComponent } from 'app/entities/m-rival-encount-cutin/m-rival-encount-cutin-detail.component';
import { MRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';

describe('Component Tests', () => {
  describe('MRivalEncountCutin Management Detail Component', () => {
    let comp: MRivalEncountCutinDetailComponent;
    let fixture: ComponentFixture<MRivalEncountCutinDetailComponent>;
    const route = ({ data: of({ mRivalEncountCutin: new MRivalEncountCutin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRivalEncountCutinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MRivalEncountCutinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRivalEncountCutinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mRivalEncountCutin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
