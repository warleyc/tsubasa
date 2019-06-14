/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNgWordDetailComponent } from 'app/entities/m-ng-word/m-ng-word-detail.component';
import { MNgWord } from 'app/shared/model/m-ng-word.model';

describe('Component Tests', () => {
  describe('MNgWord Management Detail Component', () => {
    let comp: MNgWordDetailComponent;
    let fixture: ComponentFixture<MNgWordDetailComponent>;
    const route = ({ data: of({ mNgWord: new MNgWord(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNgWordDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MNgWordDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MNgWordDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mNgWord).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
