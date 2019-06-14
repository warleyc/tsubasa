/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformBottomDetailComponent } from 'app/entities/m-uniform-bottom/m-uniform-bottom-detail.component';
import { MUniformBottom } from 'app/shared/model/m-uniform-bottom.model';

describe('Component Tests', () => {
  describe('MUniformBottom Management Detail Component', () => {
    let comp: MUniformBottomDetailComponent;
    let fixture: ComponentFixture<MUniformBottomDetailComponent>;
    const route = ({ data: of({ mUniformBottom: new MUniformBottom(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformBottomDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MUniformBottomDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MUniformBottomDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mUniformBottom).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
