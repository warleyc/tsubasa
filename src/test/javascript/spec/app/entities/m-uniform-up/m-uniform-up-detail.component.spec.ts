/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformUpDetailComponent } from 'app/entities/m-uniform-up/m-uniform-up-detail.component';
import { MUniformUp } from 'app/shared/model/m-uniform-up.model';

describe('Component Tests', () => {
  describe('MUniformUp Management Detail Component', () => {
    let comp: MUniformUpDetailComponent;
    let fixture: ComponentFixture<MUniformUpDetailComponent>;
    const route = ({ data: of({ mUniformUp: new MUniformUp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformUpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MUniformUpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MUniformUpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mUniformUp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
