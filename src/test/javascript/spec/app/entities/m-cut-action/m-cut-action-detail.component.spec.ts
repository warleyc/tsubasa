/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCutActionDetailComponent } from 'app/entities/m-cut-action/m-cut-action-detail.component';
import { MCutAction } from 'app/shared/model/m-cut-action.model';

describe('Component Tests', () => {
  describe('MCutAction Management Detail Component', () => {
    let comp: MCutActionDetailComponent;
    let fixture: ComponentFixture<MCutActionDetailComponent>;
    const route = ({ data: of({ mCutAction: new MCutAction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutActionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCutActionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCutActionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCutAction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
