/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionDetailComponent } from 'app/entities/m-action/m-action-detail.component';
import { MAction } from 'app/shared/model/m-action.model';

describe('Component Tests', () => {
  describe('MAction Management Detail Component', () => {
    let comp: MActionDetailComponent;
    let fixture: ComponentFixture<MActionDetailComponent>;
    const route = ({ data: of({ mAction: new MAction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MActionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
