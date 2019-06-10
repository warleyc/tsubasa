/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersCommandCompatibilityDetailComponent } from 'app/entities/m-encounters-command-compatibility/m-encounters-command-compatibility-detail.component';
import { MEncountersCommandCompatibility } from 'app/shared/model/m-encounters-command-compatibility.model';

describe('Component Tests', () => {
  describe('MEncountersCommandCompatibility Management Detail Component', () => {
    let comp: MEncountersCommandCompatibilityDetailComponent;
    let fixture: ComponentFixture<MEncountersCommandCompatibilityDetailComponent>;
    const route = ({ data: of({ mEncountersCommandCompatibility: new MEncountersCommandCompatibility(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersCommandCompatibilityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MEncountersCommandCompatibilityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEncountersCommandCompatibilityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mEncountersCommandCompatibility).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
