/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDictionaryItDetailComponent } from 'app/entities/m-dictionary-it/m-dictionary-it-detail.component';
import { MDictionaryIt } from 'app/shared/model/m-dictionary-it.model';

describe('Component Tests', () => {
  describe('MDictionaryIt Management Detail Component', () => {
    let comp: MDictionaryItDetailComponent;
    let fixture: ComponentFixture<MDictionaryItDetailComponent>;
    const route = ({ data: of({ mDictionaryIt: new MDictionaryIt(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDictionaryItDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDictionaryItDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDictionaryItDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDictionaryIt).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
