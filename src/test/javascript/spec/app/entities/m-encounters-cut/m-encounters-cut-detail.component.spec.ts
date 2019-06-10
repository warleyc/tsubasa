/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersCutDetailComponent } from 'app/entities/m-encounters-cut/m-encounters-cut-detail.component';
import { MEncountersCut } from 'app/shared/model/m-encounters-cut.model';

describe('Component Tests', () => {
  describe('MEncountersCut Management Detail Component', () => {
    let comp: MEncountersCutDetailComponent;
    let fixture: ComponentFixture<MEncountersCutDetailComponent>;
    const route = ({ data: of({ mEncountersCut: new MEncountersCut(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersCutDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MEncountersCutDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEncountersCutDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mEncountersCut).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
