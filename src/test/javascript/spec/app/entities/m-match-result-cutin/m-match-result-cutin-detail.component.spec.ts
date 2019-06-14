/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchResultCutinDetailComponent } from 'app/entities/m-match-result-cutin/m-match-result-cutin-detail.component';
import { MMatchResultCutin } from 'app/shared/model/m-match-result-cutin.model';

describe('Component Tests', () => {
  describe('MMatchResultCutin Management Detail Component', () => {
    let comp: MMatchResultCutinDetailComponent;
    let fixture: ComponentFixture<MMatchResultCutinDetailComponent>;
    const route = ({ data: of({ mMatchResultCutin: new MMatchResultCutin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchResultCutinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMatchResultCutinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMatchResultCutinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMatchResultCutin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
