/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryZhDetailComponent } from 'app/entities/m-dictionary-zh/m-dictionary-zh-detail.component';
import { MDictionaryZh } from 'app/shared/model/m-dictionary-zh.model';

describe('Component Tests', () => {
  describe('MDictionaryZh Management Detail Component', () => {
    let comp: MDictionaryZhDetailComponent;
    let fixture: ComponentFixture<MDictionaryZhDetailComponent>;
    const route = ({ data: of({ mDictionaryZh: new MDictionaryZh(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryZhDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryZhDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryZhDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryZh).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
