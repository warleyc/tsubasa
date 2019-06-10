/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCharacterBookDetailComponent } from 'app/entities/m-character-book/m-character-book-detail.component';
import { MCharacterBook } from 'app/shared/model/m-character-book.model';

describe('Component Tests', () => {
  describe('MCharacterBook Management Detail Component', () => {
    let comp: MCharacterBookDetailComponent;
    let fixture: ComponentFixture<MCharacterBookDetailComponent>;
    const route = ({ data: of({ mCharacterBook: new MCharacterBook(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCharacterBookDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCharacterBookDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCharacterBookDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCharacterBook).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
