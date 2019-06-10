/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCutShootOrbitDetailComponent } from 'app/entities/m-cut-shoot-orbit/m-cut-shoot-orbit-detail.component';
import { MCutShootOrbit } from 'app/shared/model/m-cut-shoot-orbit.model';

describe('Component Tests', () => {
  describe('MCutShootOrbit Management Detail Component', () => {
    let comp: MCutShootOrbitDetailComponent;
    let fixture: ComponentFixture<MCutShootOrbitDetailComponent>;
    const route = ({ data: of({ mCutShootOrbit: new MCutShootOrbit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutShootOrbitDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCutShootOrbitDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCutShootOrbitDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCutShootOrbit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
