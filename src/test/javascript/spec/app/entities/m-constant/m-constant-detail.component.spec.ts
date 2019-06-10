/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MConstantDetailComponent } from 'app/entities/m-constant/m-constant-detail.component';
import { MConstant } from 'app/shared/model/m-constant.model';

describe('Component Tests', () => {
  describe('MConstant Management Detail Component', () => {
    let comp: MConstantDetailComponent;
    let fixture: ComponentFixture<MConstantDetailComponent>;
    const route = ({ data: of({ mConstant: new MConstant(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MConstantDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MConstantDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MConstantDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mConstant).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
