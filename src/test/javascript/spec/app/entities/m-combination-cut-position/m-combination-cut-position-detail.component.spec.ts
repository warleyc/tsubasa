/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCombinationCutPositionDetailComponent } from 'app/entities/m-combination-cut-position/m-combination-cut-position-detail.component';
import { MCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';

describe('Component Tests', () => {
  describe('MCombinationCutPosition Management Detail Component', () => {
    let comp: MCombinationCutPositionDetailComponent;
    let fixture: ComponentFixture<MCombinationCutPositionDetailComponent>;
    const route = ({ data: of({ mCombinationCutPosition: new MCombinationCutPosition(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCombinationCutPositionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCombinationCutPositionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCombinationCutPositionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCombinationCutPosition).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
