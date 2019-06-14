/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MKeeperCutActionDetailComponent } from 'app/entities/m-keeper-cut-action/m-keeper-cut-action-detail.component';
import { MKeeperCutAction } from 'app/shared/model/m-keeper-cut-action.model';

describe('Component Tests', () => {
  describe('MKeeperCutAction Management Detail Component', () => {
    let comp: MKeeperCutActionDetailComponent;
    let fixture: ComponentFixture<MKeeperCutActionDetailComponent>;
    const route = ({ data: of({ mKeeperCutAction: new MKeeperCutAction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MKeeperCutActionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MKeeperCutActionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MKeeperCutActionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mKeeperCutAction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
