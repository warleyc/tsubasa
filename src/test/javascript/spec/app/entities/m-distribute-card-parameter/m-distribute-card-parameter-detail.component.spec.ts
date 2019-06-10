/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDistributeCardParameterDetailComponent } from 'app/entities/m-distribute-card-parameter/m-distribute-card-parameter-detail.component';
import { MDistributeCardParameter } from 'app/shared/model/m-distribute-card-parameter.model';

describe('Component Tests', () => {
  describe('MDistributeCardParameter Management Detail Component', () => {
    let comp: MDistributeCardParameterDetailComponent;
    let fixture: ComponentFixture<MDistributeCardParameterDetailComponent>;
    const route = ({ data: of({ mDistributeCardParameter: new MDistributeCardParameter(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDistributeCardParameterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDistributeCardParameterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDistributeCardParameterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDistributeCardParameter).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
