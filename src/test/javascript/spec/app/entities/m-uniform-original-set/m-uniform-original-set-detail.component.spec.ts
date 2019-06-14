/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUniformOriginalSetDetailComponent } from 'app/entities/m-uniform-original-set/m-uniform-original-set-detail.component';
import { MUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';

describe('Component Tests', () => {
  describe('MUniformOriginalSet Management Detail Component', () => {
    let comp: MUniformOriginalSetDetailComponent;
    let fixture: ComponentFixture<MUniformOriginalSetDetailComponent>;
    const route = ({ data: of({ mUniformOriginalSet: new MUniformOriginalSet(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUniformOriginalSetDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MUniformOriginalSetDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MUniformOriginalSetDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mUniformOriginalSet).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
